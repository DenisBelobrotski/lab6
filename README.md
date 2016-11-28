Лабораторная работа 6.  Работа с различными форматами хранения данных (CSV, XML, JSON)
================================================================================================

Разработать консольное приложение на Java.
 
Постановка задачи
-------------------------------------------------------------------------------------------------------
Необходимо прочитать данные, обработать их и записать выходные данные в заданном формате. 
 
Входные данные
---------------------------------------------------------------------------------------------------------------
Входной файл input содержит данные в формате CSV.
Каждая запись в файле расположена на новой строке.
Разделителем между полями одной записи является символ точка с запятой (';').
Если значения какого-то поля записи нет, то разделить все равно присутствует.
Обязательными для заполнения являются только те поля, по которым строятся запросы для выборки данных.
 
Формат входных данных
-------------------------------------------------------------------------------------------------------------
Имеется список компаний.
Каждый элемент списка содержит следующие поля:

    * Наименование (name)
    * Краткое наименование (shortTitle)
    * Дата актуализации (dateUpdate)
    * Адрес (address)
    * Дата основания (dateFoundation)
    * Численность сотрудников (countEmployees)
    * Аудитор (auditor)
    * Телефон (phone)
    * Email (email)
    * Отрасль (branch)
    * Вид деятельности (activity)
    * Адрес страницы в Интернет (internetAddress/link)
 
Выходные данные
---------------------------------------------------------------------------------------------------------------
1. Прочитать данные из входного файла.
2. Выбрать данные по запросу.
3. Записать полученные данные в два файла (в XML-формате и JSON).
 
Запросы
-------------------------------------------------------------------------------------------------------------
1. Найти компанию по краткому наименованию. 
2. Выбрать компании по отрасли. 
3. Выбрать компании по виду деятельности.
4. Выбрать компании по дате основания в определенном промежутке (с и по).
5. Выбрать компании по численности сотрудников в определенном промежутке (с и по).
 
Примечания
---------------------------------------------------------------------------------------------------
1. Ваша программа должна игнорировать различие между строчными и прописными буквами.
2. Необходимо вести статистику работы приложения в файле logfile.txt. 

Лабораторная работа 7. Работа с файлами
===================================================

Разработать консольное приложение на Java.
 
Постановка задачи
-----------------------------------------------------
Необходимо для задачи лабораторной работы 6 предусмотреть ввод условия для выбора информации с помощью SQL-запроса. 
 
Для выполнения лабораторной работы:
 
1. Изучите синтаксис построения SQL-запроса SELECT.
    Рассмотрите варианты написания запроса без использования именования столбцов.
    Имя таблицы для запроса: dataBase.
    Регистр написания ключевых слов и наименований полей в SQL-запросе не учитывайте.
2. Выполните разбор поступившего SQL-запроса.
3. Выполните выбор информации в соответствии с запросом.
 
Входные данные
------------------------
Запросы расположите во входном текстовом файле requests.txt построчно (каждый запрос на новой строке).
 
Выходные данные
--------------------------
Выходные файлы именуйте с номером, соответствующим порядковому номеру запроса во входном файле. 
Например, request1.xml, request1.json, request2.xml, request2.json и т.д.
 
Запросы
-------------------------------
1. Найти компанию по краткому наименованию.
2. Выбрать компании по виду деятельности.
3. Выбрать компании по численности сотрудников в определенном промежутке (с и по).
 
Примечания
----------------------------------
1. Ваша программа должна игнорировать различие между строчными и прописными буквами.
2. Необходимо вести статистику работы приложения в файле logfile.txt (дата и время запуска приложения, успешно ли выполнено, исключения и т.п.).  
