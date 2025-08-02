package com.group7.g7_trivia_game;

import android.content.Context;
import android.content.Intent;

/**
 * description
 *
 * @author
 * @since 7/31/2025
 */
public class IntentFactory {

    /**
     * Creates new intent for the account creation activity.
     * Takes no extras.
     * @param context Application context
     * @return createAccount intent
     */
    static Intent createAccountActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, CreateAccountActivity.class);
        return intent;
    }
}
