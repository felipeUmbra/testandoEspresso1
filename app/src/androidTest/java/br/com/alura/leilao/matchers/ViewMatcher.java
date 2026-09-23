package br.com.alura.leilao.matchers;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Matcher;

import java.util.Objects;

import br.com.alura.leilao.R;

public class ViewMatcher {
    public static Matcher<? super View> displaysLeilaoOnPosition(final int position, final String expectedDescricao, final String expectedMaiorLance) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            private Matcher<View> displayed = isDisplayed();
            private boolean itHasText(View itemView, int itemID, String expectedValue) {
                TextView foundText = itemView.findViewById(itemID);
                return foundText.getText()
                        .toString().equals(expectedValue);
            }

            @Override
            protected boolean matchesSafely(RecyclerView item) {
                View itemView = Objects.requireNonNull(item.findViewHolderForAdapterPosition(position)).itemView;

                if (item.findViewHolderForAdapterPosition(position) == null) {
                    throw new IndexOutOfBoundsException("Invalid position");
                }

                boolean hasDescricao = itHasText(itemView, R.id.item_leilao_descricao, expectedDescricao);
                boolean hasMaiorLance = itHasText(itemView, R.id.item_leilao_maior_lance, expectedMaiorLance);
                boolean viewHolderIsDisplayed = isDisplayed().matches(itemView);

                return hasDescricao && hasMaiorLance && viewHolderIsDisplayed;
            }

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("View with description ")
                        .appendValue(expectedDescricao)
                        .appendText(", and value")
                        .appendValue(expectedMaiorLance)
                        .appendText(" not found");
                description.appendDescriptionOf(displayed);
            }
        };
    }

}
