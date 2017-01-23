package app.subbu.carbuy.entity;

/**
 * Created by Subramanyam on 23-Jan-2017.
 */

public class Filter {

    private Manufacturer manufacturer;
    private Model model;
    private BuiltDate builtDate;

    public Filter(Manufacturer manufacturer, Model model, BuiltDate builtDate) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.builtDate = builtDate;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public BuiltDate getBuiltDate() {
        return builtDate;
    }

    public void setBuiltDate(BuiltDate builtDate) {
        this.builtDate = builtDate;
    }
}
