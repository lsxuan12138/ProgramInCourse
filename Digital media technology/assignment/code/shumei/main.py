import torch
import torch.nn as nn

from torch.utils.data import DataLoader
from torchvision import transforms

from srcnn import ImgDataset, SRCNN

image_path = "E:/Project/PycharmProjects/spider/image"
green_img_path = "E:/Project/PycharmProjects/spider/green"
compress_img_path = "E:/Project/PycharmProjects/spider/compress"
train_dataset = ImgDataset(hr_path=image_path, lr_path=compress_img_path,
                           transform=transforms.Compose([transforms.ToTensor()]))
train_loader = DataLoader(train_dataset, batch_size=8)

model = SRCNN()
optimizer = torch.optim.Adam(model.parameters(), lr=0.001)
criterion = nn.MSELoss()

device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
model.to(device)

epochs = 100
for epoch in range(epochs):
    for i, (lr_images, hr_images) in enumerate(train_loader):
        lr_images, hr_images = lr_images.to(device), hr_images.to(device)

        optimizer.zero_grad()
        output = model(lr_images)
        loss = criterion(output, hr_images)
        loss.backward()
        optimizer.step()

        if i % 10 == 0:
            print('Epoch [{}/{}], Step [{}/{}], Loss: {:.4f}'.format(
                epoch + 1, epochs, i + 1, len(train_loader), loss.item()))
torch.save(model.state_dict(), 'srcnn_model_compress.pth')
