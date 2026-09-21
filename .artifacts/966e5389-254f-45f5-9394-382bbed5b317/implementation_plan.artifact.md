# Fix Espresso Synchronization and Test Stability

The test `displayLeilao_afterLoadAPI` is failing because Espresso checks the view hierarchy before the RecyclerView has finished rendering its items, even with the current IdlingResource implementation. This is likely due to the `decrement()` call happening before the UI thread finishes processing the adapter update.

## Proposed Changes

### Idling Resource Refinement

#### [MODIFY] [AppIdlingResource.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/java/br/com/alura/leilao/api/idlingresource/AppIdlingResource.java)
- Ensure `decrement()` is safe and potentially add a small delay or post it to the main thread to ensure all UI messages are processed. Actually, Espresso should handle the main thread idle state, but we'll ensure the counter reflects the full operation.

### Network Client Fixes

#### [MODIFY] [LeilaoWebClient.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/java/br/com/alura/leilao/api/retrofit/client/LeilaoWebClient.java)
- Wrap the `decrement()` call in a `Handler().post()` to ensure it stays in the queue after the UI update call.

### Test Synchronization

#### [MODIFY] [ListaLeilaoScreenTest.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/androidTest/java/br/com/alura/leilao/ui/activity/ListaLeilaoScreenTest.java)
- Switch from `@Rule ActivityScenarioRule` to manual `ActivityScenario.launch()` in the test method. This allows registering the `IdlingResource` **before** the activity is even created, avoiding any race conditions during the initial launch and data fetch.
- Add a cleanup step in `@After` to close the scenario.

## Verification Plan

### Automated Tests
- Run `displayLeilao_afterLoadAPI` from the IDE (as shell execution is currently unstable in this environment).
- Confirm the test passes consistently.
