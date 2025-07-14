# NoteManager
A small, simple note management application written in java.

# Showcase
![showcase](./showcase.png)

# Building
Since the application is built in java, building is quite simple.

## Linux

### Prerequisities
```bash
# Ubuntu
sudo apt install default-jdk default-jre maven libfuse2 podman

# Arch
sudo pacman -S jdk21-openjdk maven podman fuse2
```
### Installing
This app relies on AppImage to run, I've created a simple podman container for it.
```bash
chmod +x ./mkimg.sh

# To use podman
./mkimg.sh

# Alternatively, you can use docker
./mkimg.sh --docker
```

If the installation returned no errors, you can simply run like this:
```bash
chmod +x ./NoteManager.AppImage
./NoteManager.AppImage
```

## Windows
:(

# License
GPL 3.0