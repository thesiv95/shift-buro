//-----------------------------------------Отображение просьб-----------------------------------------
// Статус просьбы: актуально ли
const isActive = (bool, id) =>
{
  if(bool)
  {
    return `<div class="card_status" id="card_status${id}">Статус: актуально</div>`;
  }
  return `<div class="card_status" id="card_status${id}">Статус: неактуально</div>`;
}

// Вывод информации о просьбе
const renderCard = card => `
  <div class="card">
    <div class="card_task">${card.TASK}</div>
    ` + isActive(card.STATUS, card.ID) +
    `<div class="card_type">Категория: ${card.TYPE}</div>
    <div class="card_owner-name">Имя: ${card.OWNERNAME}</div>
    <div class="card_phone">Телефон: ${card.PHONE}</div>
    <div class="card_city">Город: ${card.CITY}</div>
    <div class="card_description">${card.DESCRIPTION}</div>
    <button class="button" onclick="acceptCard(${card.ID})">Принять</button>
    <div class="card_price">${card.PRICE}</div>
  </div>
`;

// Вывод списка всех просьб
const getAllCards = function() {
  createRequest({ path: "CARD", method: "GET"})
    .then(response => {
      document.querySelector("#cards").innerHTML = response
        .map(renderCard)
        .join("");
      console.log("Результат запроса просьб", response);
    })
    .catch(err => {
      document.querySelector("#cards").innerHTML =
        "Не удалось получить список просьб";
      console.log("Ошибка при получении списка просьб", err);
    });
};

getAllCards();

// Нажатие кнопки "Принять"
function acceptCard(id) {
  createRequest({ path: `changeBalance/${id}`, method: "POST" })
  .then(response => {
    document.querySelector(`#card_status${id}`).innerHTML = "Cтатус: неактуально";
  })
  .catch(err => {
    document.querySelector("#cards").innerHTML =
      "Не удалось отобразить просьбу";
  }); 
}
