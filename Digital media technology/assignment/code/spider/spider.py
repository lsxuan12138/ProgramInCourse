import time

import requests
import re
import os

url = "https://m.qqtn.com"
headers = {
    "user-agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 "
                  "Safari/537.36"
}
path_root = "./image"


# 获取所有类型的路径
def get_types():
    more_url = "/tx_more.html"
    response = requests.get(url=url + more_url, headers=headers)
    response.encoding = response.apparent_encoding
    # 打印请求成功的网页源码，和在网页右键查看源代码的内容一样的
    # print(response.text)

    pattern = r'<li><a href="/tx/.*?">.*?</a></li>'
    types = re.findall(pattern, response.text)

    pattern1 = r'<a href="([^"]*)"'
    result = set()
    for t in types:
        # print(t)
        match = re.search(pattern1, t)
        # print(match.group(1))
        result.add(match.group(1))
    # print(result)
    with open("./types.txt", "w") as f:
        for t in result:
            f.write(t)
            f.write("\n")
        f.flush()
        f.close()
    return result


def get_contents(types):
    result = set()
    count = 1
    for t in types:
        response = requests.get(url=url + t, headers=headers)
        response.encoding = response.apparent_encoding
        pattern = r'<a href="(/c/\d+)"'
        string = response.text

        match = re.search(pattern, string)
        while match:
            # print(match.group(1))
            result.add(match.group(1))
            string = string[match.end():]
            match = re.search(pattern, string)
        if count >= 10:
            count = 1
            print(f"正在获取type：“{t}”，中的contents")
        else:
            count = count + 1
    with open("./contents.txt", "w") as f:
        for c in result:
            f.write(c)
            f.write("\n")
        f.flush()
        f.close()
    return result


def get_images(contents):
    result = set()
    count = 1
    for c in contents:
        response = requests.get(url=url + c, headers=headers)
        response.encoding = response.apparent_encoding
        pattern = r'<img src="(https://p.qqan.com/up/\d+-\d+/\d+.jpg)"/>'
        string = response.text
        match = re.search(pattern, string)
        while match:
            # print(match.group(1))
            result.add(match.group(1))
            string = string[match.end():]
            match = re.search(pattern, string)
        if count >= 10:
            count = 1
            print(f"正在获取content：“{c}”，中的images")
        else:
            count = count + 1
    with open("./images.txt", "w") as f:
        for image in result:
            f.write(image)
            f.write("\n")
        f.flush()
        f.close()
    return result


def download_images(images):
    if not os.path.isdir(path_root):
        os.mkdir(path_root)
    # image = 'https://p.qqan.com/up/2022-6/16544017802592435.jpg'
    pattern = r'https://p.qqan.com/up/(\d+-\d+)/(\d+).jpg'
    count = 1
    for image in images:
        match = re.search(pattern, image)
        filename = f"{match.group(1)}_{match.group(2)}.jpg"
        with open(path_root + "/" + filename, "wb") as img:
            res = requests.get(image)
            img.write(res.content)
            img.close()
        if count >= 10:
            count = 1
            print(f"正在获取image：“{image}”")
        else:
            count = count + 1
        time.sleep(1)


if __name__ == '__main__':

    # 获取types，存到文件
    types = get_types()
    print(f"得到types:{len(types)}")

    # 从文件读取types，获取contents
    # types = set()
    # f = open("./types.txt", "r")
    # line = f.readline().strip()
    # while line:
    #     types.add(line)
    #     line = f.readline().strip()
    # f.close()
    # contents = get_contents(types=types)
    # print(f"得到contents:{len(contents)}")

    # 从文件读取contents，获取images
    # contents = set()
    # f = open("./contents.txt", "r")
    # for i in range(200):
    #     line = f.readline().strip()
    #     contents.add(line)
    # f.close()
    # images = get_images(contents=contents)
    # print(f"得到images:{len(images)}")

    # images = set()
    # f = open("./images.txt", "r")
    # for i in range(1000):
    #     line = f.readline().strip()
    #     images.add(line)
    # f.close()
    # download_images(images=images)
