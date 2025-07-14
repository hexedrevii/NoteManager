FROM ubuntu:24.04 AS builder

ENV DEBIAN_FRONTEND=noninteractive

RUN apt update -y && apt upgrade -y && apt install -y \
    build-essential curl default-jre default-jdk maven xz-utils file \
    && rm -rf /var/lib/apt/lists/*

# Download
RUN curl -L -o /usr/local/bin/appimagetool \
    https://github.com/AppImage/appimagetool/releases/download/continuous/appimagetool-x86_64.AppImage && \
    chmod +x /usr/local/bin/appimagetool

WORKDIR /app
COPY . .

RUN mvn javafx:jlink
RUN mkdir -p AppImage/usr \
    && cp -r ./target/app/* ./AppImage/usr \
    && cp -r ./data/* ./AppImage \
    && chmod +x ./AppImage/AppRun

# Use built-in FUSE
ENV APPIMAGE_EXTRACT_AND_RUN=1

RUN appimagetool ./AppImage

# Rename it into something more recognisable.
RUN rm -f ./NoteManager.AppImage && mv *.AppImage ./NoteManager.AppImage