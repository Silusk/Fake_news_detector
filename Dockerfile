FROM openjdk:11-jdk-slim
RUN apt-get update && apt-get install .y python3 python3-pip && rm -rf /var/lib/apt/lists/*
WORKIDIR/app
COPY requirements.txt
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
RUN javac FakeNewsDetector.java
CMD ["java","FakeNewsDetector"]