package step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.*;

public class ListComparisonSteps {
    private final TestContext context;

    public ListComparisonSteps(TestContext context) {
        this.context = context;
    }

    @When("I compare both lists")
    public void i_compare_both_lists() {
        List<Map<String, String>> firstList = context.getExpectedProducts().asMaps();
        List<Map<String, String>> secondList = context.getActualProducts().asMaps();

        List<Map<String, String>> missingProductsInSecondList = collectMissingProducts(firstList, secondList);
        List<Map<String, String>> missingProductsInFirstList = collectMissingProducts(secondList, firstList);

        for (Map<String, String> item: missingProductsInSecondList) {
            context.addDiscrepancies("The second list does not include the product '" + item.get("name") + "'");
        }

        for (Map<String, String> item: missingProductsInFirstList) {
            context.addDiscrepancies("The first list does not include the product '" + item.get("name") + "'");
        }

        for (Map<String, String> item: firstList) {
            for (Map<String, String> compareItem: secondList) {
                if (item.get("name").equals(compareItem.get("name"))) {
                    if (!item.get("price").equals(compareItem.get("price"))) {
                        context.addDiscrepancies("The price of '" + item.get("name") + "' is '" + item.get("price") + "' in the first table and '" + compareItem.get("price") + "' in the second." );
                    }

                    if (!item.get("category").equals(compareItem.get("category"))) {
                        context.addDiscrepancies("The category of '" + item.get("name") + "' is '" + item.get("category") + "' in the first table and '" + compareItem.get("category") + "' in the second." );
                    }
                }

            }
        }
    }

    @Then("the lists should contain the same items with name, price, and category, regardless of order")
    public void the_lists_should_contain_the_same_items_with_name_price_and_category_regardless_of_order() {
        List<String> discrepancies = context.getDiscrepancies();
        if (!discrepancies.isEmpty()) {
            Assert.fail("Discrepancies found:\n" + String.join("\n", discrepancies));
        }
    }

    public List<Map<String, String>> collectMissingProducts(List<Map<String, String>> firstList, List<Map<String, String>> secondList) {
        List<Map<String, String>> missingProducts = new ArrayList<>();

        for (Map<String, String> item: firstList) {
            boolean isFound = false;
            for (Map<String, String> compareItem: secondList) {
                if (item.get("name").equals(compareItem.get("name"))) {
                    isFound = true;
                }
            }
            if (!isFound) {
                missingProducts.add(item);
            }
        }
        return missingProducts;
    }
}
