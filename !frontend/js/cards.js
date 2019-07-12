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

// Склонение слова "баллы"
const foo = price =>
{
  if (price % 10 == 1)
  {
    return `${price} балл`;
  }
  if (price % 10 == 2 || price % 10 == 3 || price % 10 == 4)
  {
    return `${price} балла`
  }
  return `${price} баллов`;
}

// Вывод информации о просьбе
const renderCard = card => `
  <div class="card">
    <div class="card_task">${card.TASK}</div>
    ` + isActive(card.STATUS, card.ID) +
    `<div class="card_fields">
      <div class="card_fields_line">
        <div class="card_fields_line_title">Категория:</div>
        <div class="card_fields_line_field">${card.TYPE}</div>
      </div>
      <div class="card_fields_line">
        <div class="card_fields_line_title">Имя:</div>
        <div class="card_fields_line_field">${card.OWNERNAME}</div>
      </div>
      <div class="card_fields_line">
        <div class="card_fields_line_title">Телефон:</div>
        <div class="card_fields_line_field">${card.PHONE}</div>
      </div>
      <div class="card_fields_line">
        <div class="card_fields_line_title">Город:</div>
        <div class="card_fields_line_field">${card.CITY}</div>
      </div>
    </div>
    <div class="card_description">${card.DESCRIPTION}</div>
    <div class="card_accept">
      <div class="card_accept_button">
        <button class="card_button" onclick="acceptCard(${card.ID})">Принять</button>
      </div>
      <div class="card_price">` + foo(card.PRICE) + `</div>
    </div>
  </div>
`;

// Вывод списка всех просьб
const getAllCards = function() {
  createRequest({ path: "CARD", method: "GET"}) // ПУТЬ И ЗАПРОС
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

// Нажатие выпадающего списка "Выберите категорию"
const categorySelector = document.querySelector('.type_select');
categorySelector.addEventListener('change', event => {
  let string = event.target.value; // select || request || offer
  console.log(string);
  if (string === "Просьба" || string === "Предложение") {
    createRequest({ path: "cards/getTypedCards/" + string, method: "GET"}) // ПУТЬ И ЗАПРОС
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
  }
});

// Нажатие кнопки "Принять"
function acceptCard(id) {
  createRequest({ path: `changeBalance/${id}`, method: "POST" }) // ПУТЬ И ЗАПРОС, ТЕЛА НЕТ
  .then(response => {
    document.querySelector(`#card_status${id}`).innerHTML = "Cтатус: неактуально";
  })
  .catch(err => {
    document.querySelector(`#card_status${id}`).innerHTML =
      "Не удалось принять просьбу";
  }); 
}
