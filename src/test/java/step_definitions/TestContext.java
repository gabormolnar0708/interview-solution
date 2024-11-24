package step_definitions;

import io.cucumber.datatable.DataTable;
import java.util.ArrayList;
import java.util.List;

public class TestContext {
    private DataTable actualProducts;
    private DataTable expectedProducts;
    private final List<String> discrepancies = new ArrayList<>();


    public DataTable getActualProducts() {
        return actualProducts;
    }

    public DataTable getExpectedProducts() {
        return expectedProducts;
    }

    public void setActualProducts(DataTable actualProducts) {
        this.actualProducts = actualProducts;
    }

    public void setExpectedProducts(DataTable expectedProducts) {
        this.expectedProducts = expectedProducts;
    }

    public List<String> getDiscrepancies() {
        return discrepancies;
    }

    public void addDiscrepancies(String discrepancy) {
        this.discrepancies.add(discrepancy);
    }
}
