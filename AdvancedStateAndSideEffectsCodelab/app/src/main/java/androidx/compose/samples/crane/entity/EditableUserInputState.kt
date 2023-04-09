package androidx.compose.samples.crane.entity

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

class EditableUserInputState(private val hint: String, initialText: String) {
    var text by mutableStateOf(initialText)

    val isHint: Boolean
        get() = text == hint

    companion object {
        val Saver: Saver<EditableUserInputState, *> = listSaver(
            save = { listOf(it.hint, it.text) },
            restore = { EditableUserInputState(it[0], it[1])}
        )
    }

}

@Composable
fun rememberEditableUserInputState(hint: String) =
    rememberSaveable(hint, saver = EditableUserInputState.Saver) {
        EditableUserInputState(hint, hint)
    }