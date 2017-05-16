/**
 * Created by Andrew on 16.05.2017.
 */
function confirmDelete() {

    if (confirm("Вы подтверждаете удаление?")) {
        return true;
    } else {
        return false;
    }
}