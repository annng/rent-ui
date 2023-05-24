package com.crocodic.core.helper

import java.io.File


class FileHelper {

    companion object {
        fun deleteFolder(fileOrDirectory: File) {
            if (fileOrDirectory.isDirectory) {
                for (child in fileOrDirectory.listFiles())
                    deleteFolder(child)
            }
            fileOrDirectory.delete()
        }
    }

}