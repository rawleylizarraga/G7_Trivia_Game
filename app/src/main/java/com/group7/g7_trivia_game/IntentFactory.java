package com.group7.g7_trivia_game;

import android.content.Context;
import android.content.Intent;

/**
 * Intent Factory file
 *
 * @author
 * @since 7/31/2025
 */
public class IntentFactory {

    /**
     * Creates new intent for the account creation activity.
     * Takes no extras.
     *
     * @param context Application context
     * @return createAccount intent
     */
    static Intent createAccountActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, CreateAccountActivity.class);
        return intent;
    }

    /**
     * Creates an intent for LoginActivity.
     * Takes no extras.
     *
     * @param context Application context
     * @return Intent
     */
    static Intent loginActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    /**
     * Creates an intent for the MainActivity.
     * Takes no extras.
     * @param context Application context
     * @return Intent
     */
    static Intent mainActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    /**
     * Creates an intent for the AdminActivity.
     * Takes no extras.
     * @param context Application context
     * @return Intent
     */
    static Intent adminActivityIntentFactory(Context context) {
        Intent intent = new Intent(context, AdminActivity.class);
        return intent;
    }
      
    /**
     * Creates an intent for the LeaderboardActivity.
     * Takes no extras.
     *
     * @param context        Application context
     * @param loggedInUserId
     * @return Intent
     */
    static Intent leaderboardIntentFactory(Context context, int loggedInUserId){
        Intent intent = new Intent(context, LeaderboardActivity.class);
        return intent;
    }
}

