package step_definitions;

import io.cucumber.java.en.Given;

public class DataTableSteps {
    private final TestContext context;

    public DataTableSteps(TestContext context) {
        this.context = context;
    }

    @Given("I have the following items in the first list:")
    public void i_have_the_following_items_in_the_first_list(io.cucumber.datatable.DataTable dataTable) {
        context.setExpectedProducts(dataTable);
    }

    @Given("I have the following items in the second list:")
    public void i_have_the_following_items_in_the_second_list(io.cucumber.datatable.DataTable dataTable) {
        context.setActualProducts(dataTable);
    }
}
