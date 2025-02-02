package app;

import storage.SvgRepository;

/**
 * Holds global state for the application:
 * - currently opened file path
 * - is a file open or not
 * - the repository containing all shapes
 */
public class AppContext {
    private boolean fileOpened;
    private String currentFile;
    private final SvgRepository repository;

    public AppContext() {
        this.fileOpened = false;
        this.currentFile = null;
        this.repository = new SvgRepository();
    }

    public boolean isFileOpened() {
        return fileOpened;
    }

    public void setFileOpened(boolean fileOpened) {
        this.fileOpened = fileOpened;
    }

    public String getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(String currentFile) {
        this.currentFile = currentFile;
    }

    public SvgRepository getRepository() {
        return repository;
    }

    /**
     * Closes the file: sets fileOpened=false, currentFile=null, and clears the repository.
     */
    public void closeFile() {
        fileOpened = false;
        currentFile = null;
        repository.clear();
    }
}
