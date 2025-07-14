#!/bin/bash

CONTAINER_ENGINE="podman"
if [ "$1" == "--docker" ]; then
  CONTAINER_ENGINE="docker"
fi

echo "Using container engine: $CONTAINER_ENGINE"

# Create the AppImage.
$CONTAINER_ENGINE build -t notemanager-builder .
$CONTAINER_ENGINE create --name extract-notemanager notemanager-builder

# Copy the built AppImage into the root of the project.
$CONTAINER_ENGINE cp extract-notemanager:/app/NoteManager.AppImage .

# Delete artefacts.
$CONTAINER_ENGINE rm extract-notemanager
$CONTAINER_ENGINE rmi notemanager-builder:latest