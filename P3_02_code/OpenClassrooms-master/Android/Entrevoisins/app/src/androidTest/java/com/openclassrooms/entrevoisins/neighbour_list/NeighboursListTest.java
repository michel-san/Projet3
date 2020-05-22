
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }
    /**
     * Delete neighbour == -Neighbours
     * //test vérifiant qu’au clic sur le bouton de suppression, la liste d’utilisateurs compte bien un utilisateur en moins
     */
    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf( withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf( withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf( withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When we click on a Neighbour, display the Activity detail
     * //test vérifiant que lorsqu’on clique sur un élément de la liste, l’écran de détails est bien lancé
     */
    @Test
    public void NeighboursListClickActionDisplayDetailActivity()
    {
        //Given : Click on the item
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Then : Go to the Detail Activity
        onView(allOf(withId(R.id.activity_detail),isDisplayed())).check(matches(isDisplayed()));
    }

    /**
     * When we click on a Neighbour, give the good Neighbour's name
     * //test vérifiant qu’au démarrage de ce nouvel écran, le TextView indiquant le nom de l’utilisateur en question est bien rempli
     */
    @Test
    public void activityDetailLoadNameTextShouldBeTheGoodOne()
    {
        //Given : A Neighbour(Caroline) for the test and When : Click on it
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Then : Have the good name(Caroline) on ActivityDetail
        onView(allOf(withId(R.id.name),isDisplayed())).check(matches(withText("Caroline")));
    }

    /**
     * The Favorite size == number of Favorites Neighbours
     * // vérifiant que l’onglet Favoris n’affiche que les voisins marqués comme favoris
     */
    @Test
    public void NeighboursFavoriteListIsEmpty() {
        //Check : Favorite list
        onView(allOf(withId(R.id.container),isDisplayed())).perform(scrollRight());
        //Given : The Favorite List is empty
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(0));
    }

    @Test
    public void NeighbourFavoriteListNoEmpty() {
        //Given : Click on the item
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        //Then : Go to the Detail Activity
        onView(allOf(withId(R.id.activity_detail), isDisplayed())).check(matches(isDisplayed()));
        //Given : Click on the item Star for add favorite neighbour
        onView(allOf(withId(R.id.fab),isDisplayed())).perform(click());
        //Then : Go back
        pressBack();
        //Check : Favorite list
        onView(allOf(withId(R.id.container),isDisplayed())).perform(scrollRight());
        //Then : favorite list should have the good favorite neighbour
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(1));
    }
}





