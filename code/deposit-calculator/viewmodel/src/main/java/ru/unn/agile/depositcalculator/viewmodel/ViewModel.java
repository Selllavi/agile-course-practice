package ru.unn.agile.depositcalculator.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.depositcalculator.model.Calculator;
import ru.unn.agile.depositcalculator.model.CapitalizationPeriod;
import ru.unn.agile.depositcalculator.model.DepositTimeType;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class ViewModel {

    // region fields
    private final SimpleStringProperty periodProperty = new SimpleStringProperty();
    private final SimpleStringProperty capitProperty = new SimpleStringProperty();

    private final SimpleStringProperty percentProperty = new SimpleStringProperty();
    private final SimpleStringProperty startSumProperty = new SimpleStringProperty();

    private final SimpleStringProperty resultProperty = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<DepositTimeType>> periods =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(DepositTimeType.values()));

    private final ObjectProperty<ObservableList<CapitalizationPeriod>> capitalizations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(CapitalizationPeriod.values()));

    private final ObjectProperty<DepositTimeType> period = new SimpleObjectProperty<>();
    private final ObjectProperty<CapitalizationPeriod> capitalization =
            new SimpleObjectProperty<>();
    // endregion

    // region getters/setters

    public ObservableList<DepositTimeType> getPeriods() {
        return periods.get();
    }
    public ObjectProperty<ObservableList<DepositTimeType>> periodsProperty() {
        return periods;
    }
    public void setPeriods(ObservableList<DepositTimeType> periods) {
        this.periods.set(periods);
    }

    public ObservableList<CapitalizationPeriod> getCapitalizations() {
        return capitalizations.get();
    }
    public ObjectProperty<ObservableList<CapitalizationPeriod>> capitalizationsProperty() {
        return capitalizations;
    }
    public void setCapitalizations(ObservableList<CapitalizationPeriod> capitalizations) {
        this.capitalizations.set(capitalizations);
    }

    public DepositTimeType getPeriod() {
        return period.get();
    }
    public ObjectProperty<DepositTimeType> periodProperty() {
        return period;
    }
    public void setPeriod(DepositTimeType period) {
        this.period.set(period);
    }

    public CapitalizationPeriod getCapitalization() {
        return capitalization.get();
    }
    public ObjectProperty<CapitalizationPeriod> capitalizationProperty() {
        return capitalization;
    }
    public void setCapitalization(CapitalizationPeriod capitalization) {
        this.capitalization.set(capitalization);
    }

    public String getPercentProperty() {
        return percentProperty.get();
    }
    public SimpleStringProperty percentProperty() {
        return percentProperty;
    }
    public void setPercentProperty(String percentProperty) {
        this.percentProperty.set(percentProperty);
    }

    public String getStartSumProperty() {
        return startSumProperty.get();
    }
    public SimpleStringProperty startSumProperty() {
        return startSumProperty;
    }
    public void setStartSumProperty(String startSumProperty) {
        this.startSumProperty.set(startSumProperty);
    }

    public String getResultProperty() {
        return resultProperty.get();
    }
    public SimpleStringProperty resultProperty() {
        return resultProperty;
    }
    public void setResultProperty(String resultProperty) {
        this.resultProperty.set(resultProperty);
    }

    //endregion

    public void calculate() {
        Calculator calculator = new Calculator();
        calculator.setPeriod(getPeriod(), 1);
        calculator.setPercent(Integer.parseInt(getPercentProperty()));
        calculator.setStartSum(Double.parseDouble(getStartSumProperty()));
        calculator.setCapitalizationPeriod(getCapitalization());
        double result = calculator.calculate();
        result = getCustomerFormat(result);
        setResultProperty(String.format("%s", result));
    }

    private double getCustomerFormat(double input) {
        BigDecimal bd = new BigDecimal(input);
        return bd.setScale(2, RoundingMode.UP).doubleValue();
    }

    public ViewModel() {
        setCapitalization(CapitalizationPeriod.MONTH);
        setPeriod(DepositTimeType.DAY);
        setStartSumProperty("1000");
        setPercentProperty("8");
    }
}
