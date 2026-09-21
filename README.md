# Android UI Testing with Espresso

This project is a dedicated environment for implementing and studying **UI Testing** on Android using the **Espresso** framework. The main goal is to demonstrate best practices for testing user interactions, handling asynchronous data from APIs, and maintaining clean, localized test data.

## 🚀 Purpose
The application is a simple auction system ("Leilão"). However, the focus of this repository is strictly on the `androidTest` folder, where the Espresso implementation resides.

## 🛠️ Testing Stack
- **[Espresso](https://developer.android.com/training/testing/espresso):** The core framework for UI assertions and interactions.
- **[ActivityScenario](https://developer.android.com/guide/components/activities/testing):** Modern API to manage Activity lifecycles during tests.
- **[Datafaker](https://www.datafaker.net/):** Used for generating localized (`pt-BR`) random data for product names, ensuring tests are dynamic and realistic.
- **[Idling Resources](https://developer.android.com/training/testing/espresso/idling-resource):** Custom implementation to synchronize Espresso with Retrofit background network calls.

## 📁 Key Files
- [**ListaLeilaoScreenTest.java**](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/androidTest/java/br/com/alura/leilao/ui/activity/ListaLeilaoScreenTest.java): The main test suite covering the Auction List screen.
- [**AppIdlingResource.java**](class://br.com.alura.leilao.api.idlingresource.AppIdlingResource): Manages the synchronization between the UI and the API.
- [**TesteWebClient.java**](class://br.com.alura.leilao.api.retrofit.client.TesteWebClient): A helper client used by tests to prepare the database state (cleaning and saving data) before launching the UI.

## 🧪 Implemented Tests
The current test suite covers:
1.  **Empty State Management**: Ensuring the database is cleaned before each run.
2.  **API Integration**: Verifying that products saved via the API are correctly rendered in the `RecyclerView`.
3.  **Dynamic Data**: Using localized Brazilian Portuguese names for generated auction items.

## 🏃 How to Run Tests
To run the instrumented tests using the custom `simulado` build type (which points to a mock API environment):

```bash
./gradlew :app:connectedSimuladoAndroidTest
```

### Build Types
- **debug**: Points to the production/local development API.
- **simulado**: Configured specifically for UI tests with a separate `URL_BASE` and `buildConfig` generation enabled.

## 📝 Best Practices Applied
- **Varargs Setup Helpers**: Using `setupDataAndLaunch(Leilao... leiloes)` to reduce boilerplate when preparing different test scenarios.
- **Manual Database Cleaning**: Ensuring test isolation by cleaning the remote database in `@Before` and `@After` hooks.
- **Localization**: Configuring `Faker` with `new Locale("pt-BR")` to match the application's domain.
