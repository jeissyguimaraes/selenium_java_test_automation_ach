FROM debian:latest

USER root

# Install dependencies, OpenJDK, Git, Maven, Chrome, and ChromeDriver
RUN apt-get update && \
    apt-get install -y gnupg2 curl git openjdk-17-jdk maven unzip wget && \
    curl -fsSL https://pkg.jenkins.io/debian/jenkins.io.key | apt-key add - && \
    sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list' && \
    apt-get update && \
    apt-get install -y jenkins && \
    rm -rf /var/lib/apt/lists/*

# Install Google Chrome
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Install ChromeDriver
ENV CHROME_DRIVER_VERSION=127.0.6533.72
RUN wget -N https://storage.googleapis.com/chrome-for-testing-public/${CHROME_DRIVER_VERSION}/linux64/chromedriver-linux64.zip -P /tmp && \
    unzip /tmp/chromedriver-linux64.zip -d /tmp && \
    mv /tmp/chromedriver /usr/local/bin/chromedriver && \
    chown root:root /usr/local/bin/chromedriver && \
    chmod 0755 /usr/local/bin/chromedriver

# Create directory for Jenkins and set permissions
RUN mkdir -p /usr/share/jenkins && \
    chmod -R 777 /usr/share/jenkins

# Download Jenkins WAR file
RUN curl -o /usr/share/jenkins/jenkins.war -fsSL https://get.jenkins.io/war-stable/latest/jenkins.war

# Ensure the Jenkins war file is present
RUN ls -l /usr/share/jenkins

# Start Jenkins
CMD ["java", "-jar", "/usr/share/jenkins/jenkins.war"]
