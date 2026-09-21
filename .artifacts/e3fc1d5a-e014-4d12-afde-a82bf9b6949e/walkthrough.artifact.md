# Walkthrough - Fixed Warnings and Errors

I have updated several files in the project to address code quality warnings, resource management issues, and improve accessibility.

## Changes Made

### 1. Model Improvement
- **[Lance.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/java/br/com/alura/leilao/model/Lance.java)**: Parameterized the `Comparable` interface to `Comparable<Lance>` and updated the `compareTo` method accordingly.

### 2. Database & Resource Management
- **[UsuarioDAO.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/java/br/com/alura/leilao/database/dao/UsuarioDAO.java)**:
    - Added `c.close()` in the `todos()` method to properly release database resources.
    - Updated `preencheUsuario` to safely handle column indexes, avoiding potential issues with `-1` returned from `getColumnIndex`.

### 3. Adapter Optimizations
- **[ListaLeilaoAdapter.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/java/br/com/alura/leilao/ui/recyclerview/adapter/ListaLeilaoAdapter.java)**: Made the `ViewHolder` class static to prevent memory leaks and updated the adapter to pass necessary dependencies (`onItemClickListener`, `formatadorDeMoeda`) to it.
- **[ListaUsuarioAdapter.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/java/br/com/alura/leilao/ui/recyclerview/adapter/ListaUsuarioAdapter.java)**:
    - Made the `ViewHolder` class static.
    - Optimized the `adiciona(List<Usuario> usuarios)` method by using `notifyItemRangeInserted` instead of notifying for each individual item in a loop.

### 4. Layout & UI Enhancements
- **[activity_lances_leilao.xml](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/res/layout/activity_lances_leilao.xml)**: Added `android:contentDescription` to the FAB for better accessibility.
- **[activity_lista_usuario.xml](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/res/layout/activity_lista_usuario.xml)**: Added `android:contentDescription` to the FAB for better accessibility.
- **[form_lance.xml](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/res/layout/form_lance.xml)**: Updated the lance value field's `inputType` to `numberDecimal` to allow entering prices with cents.

## Verification

The changes address static analysis warnings typically flagged by Android Studio (Lint) and improve the overall robustness and accessibility of the application.
