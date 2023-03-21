package file_system;

import java.time.LocalDateTime;


abstract public class FilesysObject {
    String name;
    int ownerId;
    int groupId;
    final LocalDateTime creationTime;
    LocalDateTime modifiedTime;
    LocalDateTime accessTime;
    Directory parentDirectory;

    public FilesysObject(String name) {
        this.name = name;
        LocalDateTime time = LocalDateTime.now();
        this.creationTime = time;
        this.modifiedTime = time;
        this.accessTime = time;
    }


    abstract public String getName();

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime() {
        this.modifiedTime = LocalDateTime.now();
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime() {
        this.accessTime = LocalDateTime.now();
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public Directory getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

    public String getFilePath() {
        StringBuilder path = new StringBuilder();
        if (getParentDirectory() != this) {
            path.append(getParentDirectory().getFilePath());
        }

        if (getParentDirectory() != this) {
            path.append(this.name).append("/");
        }
        else {
            path.append("/");
        }
        if (path.length() != 1) {
            path.deleteCharAt(path.length() - 1);
        }

        return path.toString();
    }

    public abstract String getColorName();
}
