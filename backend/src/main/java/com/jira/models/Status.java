package com.jira.models;

public enum Status {
    NEW,
    ASSIGNED,
    DISCUSSION,
    CANCELED,
    POSTPONED,
    COMPLETED,
    CLOSED
}
/*
1) New (Новая и никому не назначенная)
2) Assigned (Назначенная, в работе)
3) Discussion (Обсуждается, возникли вопросы)
4)Canceled ( Отмененеа,результат более не нужен, работы остановлены)
5)Postponed( Отложена ,результат нужен не срочно, есть более приоритетные задачи, работы перенесены на более поздний срок)
6) Completed (Завершена, всё сделано)
7) Closed (Закрыта, все проверили, протестировали и утвердили)
 */