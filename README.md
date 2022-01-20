# Jira

Для запуска проекта должна быть создана база данных со всеми таблицами. 
В таблице roles роли должны быть добавлены вручную!

Если база данных создана, запускаем бек из папки backend. После этого заходим в папку frontend и в терминале прописываем следующую команду:

ng serve --open

После этого откроется браузер на localhost:4200 с начальной страницей. После этого мы можем авторизироваться(в верхнем баре кнопка Login).
Если у вас отсутствует аккаунт, нужно пройти регистрацию (Sign Up). При регистрации создастся пользователь с ролью USER. Далее по этим данным авторизуемся.
Если нужны другие роли, добавляем аккаунты напрямую через бек.

Flyway migration including, hibernate show-sql is printing in console, to insert into db, change spring.jpa.hibernate.ddl-auto from 'create' to 'validate' and adding new sql script in resources.db.migrations name 'V(number of version)__(name of migration)'