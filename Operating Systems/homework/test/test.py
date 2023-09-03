import threading
import queue
import random
import time

# 全局共享队列
question_queue = queue.Queue()

# 互斥锁
mutex = threading.Lock()

# 条件变量
student_condition = threading.Condition(mutex)
tutor_condition = threading.Condition(mutex)


class Question:
    def __init__(self, id, content):
        self.id = id
        self.content = content


def student():
    question_id = 1
    while True:
        # 生成新问题
        content = f"问题内容{question_id}"
        new_question = Question(question_id, content)

        with student_condition:
            # 将新问题加入问题队列
            question_queue.put(new_question)
            print(f"学生{threading.current_thread().name}发布了问题{new_question.id}: {new_question.content}")

            # 唤醒等待的导师线程
            tutor_condition.notify_all()

        # 等待随机时间，模拟学生提问过程
        time.sleep(random.uniform(0.5, 3))

        question_id += 1


def tutor():
    while True:
        with tutor_condition:
            # 等待问题出现在队列中
            while question_queue.empty():
                tutor_condition.wait()

            # 回答队列中的问题
            question = question_queue.get()
            print(f"导师{threading.current_thread().name}回答了问题{question.id}: {question.content}")

        # 等待随机时间，模拟回答问题过程
        time.sleep(random.uniform(1, 3))


def main():
    # 创建学生和导师线程
    student_threads = [
        threading.Thread(target=student, name=f"Student-{i}")
        for i in range(3)
    ]
    tutor_threads = [
        threading.Thread(target=tutor, name=f"Tutor-{i}")
        for i in range(2)
    ]

    # 启动线程
    for t in student_threads:
        t.start()
    for t in tutor_threads:
        t.start()

    # 等待线程结束
    for t in student_threads:
        t.join()
    for t in tutor_threads:
        t.join()


if __name__ == "__main__":
    main()
