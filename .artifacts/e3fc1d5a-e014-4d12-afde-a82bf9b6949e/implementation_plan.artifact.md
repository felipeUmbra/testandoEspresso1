# Implementation Plan - Fix Warnings and Minor Errors

The goal is to address several code-level warnings and potential minor errors identified across the project files, improving code quality, resource management, and accessibility.

## User Review Required

> [!NOTE]
> Most changes are minor improvements and warning fixes. No major architectural changes are proposed.

## Proposed Changes

### Model Layer

#### [MODIFY] [Lance.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/java/br/com/alura/leilao/model/Lance.java)
- Fix raw `Comparable` type by parameterizing it as `Comparable<Lance>`.
- Update `compareTo` signature to `compareTo(Lance o)`.

### Database Layer

#### [MODIFY] [UsuarioDAO.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/java/br/com/alura/leilao/database/dao/UsuarioDAO.java)
- Close the `Cursor` in the `todos()` method to prevent resource leaks.
- Address the `getColumnIndex` warning by verifying the column index or using a safer approach.

### UI Adapters

#### [MODIFY] [ListaLeilaoAdapter.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/java/br/com/alura/leilao/ui/recyclerview/adapter/ListaLeilaoAdapter.java)
- Change `ViewHolder` to a `static` inner class to avoid potential memory leaks.

#### [MODIFY] [ListaUsuarioAdapter.java](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/java/br/com/alura/leilao/ui/recyclerview/adapter/ListaUsuarioAdapter.java)
- Change `ViewHolder` to a `static` inner class.
- Optimize the `adiciona(List<Usuario> usuarios)` method to avoid multiple `notifyItemInserted` calls in a loop.

### Layout Resources

#### [MODIFY] [activity_lances_leilao.xml](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/res/layout/activity_lances_leilao.xml)
- Add `android:contentDescription` to the `FloatingActionButton`.

#### [MODIFY] [activity_lista_usuario.xml](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/res/layout/activity_lista_usuario.xml)
- Add `android:contentDescription` to the `FloatingActionButton`.

#### [MODIFY] [form_lance.xml](file:///H:/Dev/Github/espresso1/android-teste-espresso-parte-1-projeto-inicial/app/src/main/res/layout/form_lance.xml)
- Change `android:inputType` from `number` to `numberDecimal` for the lance value field to allow cents.

## Verification Plan

### Manual Verification
- Verify that the project compiles (as much as possible given the Gradle JVM issue).
- Check if the warnings in the IDE disappear for the modified files.
