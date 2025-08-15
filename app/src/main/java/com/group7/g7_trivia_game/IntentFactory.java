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
        intent.putExtra(MainActivity.MAIN_ACTIVITY_USER_ID, userId);
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
     * @return Intent
     */
    static Intent leaderboardActivityIntentFactory(Context context){
        Intent intent = new Intent(context, LeaderboardActivity.class);
        return intent;
    }

    static Intent questionAnsweringActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, QuestionAnsweringActivity.class);
        intent.putExtra(MainActivity.MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }

    static Intent pastQuestionsActivityIntentFactory(Context context){
        Intent intent = new Intent(context, PastQuestionsActivity.class);
        return intent;
    }

    static Intent chooseCategoryActivityIntentFactory(Context context){
        Intent intent = new Intent(context, ChooseCategoryActivity.class);
        return intent;
    }

    static Intent createQuestionActivityIntentFactory(Context context){
        Intent intent = new Intent(context, CreateQuestionActivity.class);
        return intent;
    }
}

