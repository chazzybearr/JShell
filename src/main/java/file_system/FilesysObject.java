package file_system;

import java.time.LocalDateTime;


public class FilesysObject {
    String name;
    int ownerId;
    int groupId;
    final LocalDateTime creationTime;
    LocalDateTime modifiedTime;
    LocalDateTime accessTime;
    Directory parentDirectory;

    public FilesysObject(String name) {
        this.name = name;
        LocalDateTime time = java.time.LocalDateTime.now();
        this.creationTime = time;
        this.modifiedTime = time;
        this.accessTime = time;
    }


    public String getName() {
        return name;
    }

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

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
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
}
