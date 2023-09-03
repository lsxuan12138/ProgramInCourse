import os

import torch
import torch.nn as nn
from PIL import Image
from torch.utils.data import Dataset, DataLoader
from torchvision import transforms


class ImgDataset(Dataset):
    def __init__(self, hr_path, lr_path, transform=None):
        self.hr_path = hr_path
        self.lr_path = lr_path
        self.files = os.listdir(hr_path)
        self.transform = transform

    def __len__(self):
        return len(self.files)

    def __getitem__(self, idx):
        hr_img_path = os.path.join(self.hr_path, self.files[idx])
        lr_img_path = os.path.join(self.lr_path, self.files[idx])

        hr_img = Image.open(hr_img_path).convert('RGB')
        lr_img = Image.open(lr_img_path).convert('RGB')

        if self.transform:
            hr_img = self.transform(hr_img)
            lr_img = self.transform(lr_img)

        return lr_img, hr_img


class SRCNN(nn.Module):
    def __init__(self):
        super(SRCNN, self).__init__()

        self.conv1 = nn.Conv2d(3, 64, kernel_size=9, padding=4)
        self.relu1 = nn.ReLU()
        self.conv2 = nn.Conv2d(64, 32, kernel_size=1, padding=0)
        self.relu2 = nn.ReLU()
        self.conv3 = nn.Conv2d(32, 3, kernel_size=5, padding=2)

    def forward(self, x):
        out = self.conv1(x)
        out = self.relu1(out)
        out = self.conv2(out)
        out = self.relu2(out)
        out = self.conv3(out)
        return out
