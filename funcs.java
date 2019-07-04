/* Изменение баланса */
// price - количество баллов
// recipientId - кто выполнял просьбу
// donorId - чья была просьба
private void changeBalance(int price, int recipientId, int donorId){
    String query;
    
    // Добавляем баллы к получателю
    query = 'UPDATE `users` SET `balance` = `balance` + ' + price + ' WHERE `id` = ' + recipientId;
    doQuery(query); // отдельная функция для отправки запроса, а то там много кода будет
    // Снимаем баллы у донора
    query = 'UPDATE `users` SET `balance` = `balance` - ' + price + ' WHERE `id` = ' + donorId;
    doQuery(query);
}


// пример
changeBalance(25, 3, 4);

/* Закрытие заявки */
// добавить в таблицу wishes поле status
// id - id желания
private void closeWish(int id){
    query = 'UPDATE `wishes` SET `status` = "done"';
    doQuery(query);
}

// пока хз как сделать привязку ко времени

