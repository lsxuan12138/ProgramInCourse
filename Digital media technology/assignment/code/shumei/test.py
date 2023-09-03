from PIL import Image
import torch
import torchvision.transforms as transforms
import torch.nn.functional as F
from srcnn import SRCNN

# 加载已经训练好的 SRCNN 模型
model = SRCNN()
model.load_state_dict(torch.load('srcnn_model_green.pth'))
model.eval()

# 打开待恢复的图像
img_path = 'test_green.jpg'
img = Image.open(img_path).convert('RGB')

# 对图像进行处理，缩放和转换为Tensor格式
transform = transforms.Compose([
    transforms.Resize((400, 400)),
    transforms.ToTensor(),
])

img_input = transform(img)
img_input = img_input.unsqueeze(0)

# 将图像输入到模型中，获取模型输出
with torch.no_grad():
    img_output = model(img_input)

# 转换模型输出为PIL.Image 格式，并保存
img_out = transforms.ToPILImage()(img_output.squeeze())
img_out.save('restored.jpg')


def calculate_psnr(image1, image2):
    mse = F.mse_loss(image1, image2)
    psnr = 20 * torch.log10(1.0 / torch.sqrt(mse))
    return psnr.item()


src = transform(Image.open("./test.jpg").convert("RGB"))
img_in = transform(Image.open(img_path).convert("RGB"))
img_out = transform(Image.open("./restored.jpg").convert("RGB"))

print("恢复前："+str(calculate_psnr(src, img_in)))
print("恢复后："+str(calculate_psnr(src, img_out)))
