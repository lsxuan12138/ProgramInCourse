import os

import clip
import matplotlib.pyplot as plt
import torch
from annoy import AnnoyIndex
from torchvision.datasets import CIFAR100

query = "A man stands next to a tree"
index_path = "./my_index.ann"
dim = 512
cifar100 = CIFAR100(root="./cache", download=True, train=False)
device = "cuda" if torch.cuda.is_available() else "cpu"
# 加载预训练CLIP模型和预处理器
model, preprocess = clip.load('ViT-B/32', device, download_root="./cache/clip")
annoy_index = AnnoyIndex(dim, metric='angular')
if os.path.exists(index_path):
    annoy_index.load(index_path)
else:
    for i in range(len(cifar100)):
        # for i in range(10):
        # 对每个图像执行以下步骤：
        # 1. 加载图像
        # 2. 将图像转换为张量并进行预处理
        # 3. 使用预训练的CLIP模型将图像编码为特征向量
        # 4. 添加特征向量到annoy索引
        image = cifar100[i][0]
        processed_image = preprocess(image).unsqueeze(0)
        # 获取图像特征
        with torch.no_grad():
            features = model.encode_image(processed_image.to(device))
        # 将特征添加到列表中
        annoy_index.add_item(i, features.cpu().numpy().flatten())
    # 建立索引树
    annoy_index.build(10)
    annoy_index.save(index_path)

# 处理用户输入的查询文本
with torch.no_grad():
    text_features = model.encode_text(clip.tokenize(query).to(device)).squeeze()

# 在annoy索引中搜索最相似的图像
matches = annoy_index.get_nns_by_vector(
    text_features.cpu().numpy().flatten(), n=4, search_k=-1)

fig, axs = plt.subplots(2, 2, figsize=(8, 6))

# 隐藏所有刻度标签和轴
for ax in axs.flat:
    ax.axis('off')
j = 0
for i in matches:
    # 显示前4个最接近的图像
    if j == 0:
        axs[0, 0].imshow(cifar100[i][0])
    elif j == 1:
        axs[0, 1].imshow(cifar100[i][0])
    elif j == 2:
        axs[1, 0].imshow(cifar100[i][0])
    elif j == 3:
        axs[1, 1].imshow(cifar100[i][0])
    j += 1
plt.show()
