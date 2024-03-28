package one.digitalinovation.laboojava.entity;

public class Notebook extends Product {

    private NoteBook notebookType;
    public enum NoteBook {
        M2, M5, M10;
    }

    public NoteBook getNotebookType() {
        return notebookType;
    }

    public void setNotebookType(NoteBook notebookType) {
        this.notebookType = notebookType;
    }

    @Override
    public double calculateShipping() {
        return 0;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "notebookType='" + notebookType + '\'' +
                ", code='" + getCode() + '\'' +
                ", price='" + getPrice() + '\'' +
                '}';
    }
}
