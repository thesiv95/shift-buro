let userId = parseInt(window.location.search.replace(/\D+/g,""));

// Показ авы и баланса
const renderUser = user => `
  <div class="user">
    <div class="user_pic-mini">
      <img src=${user.PIC_URL}>
      <div class="user_balance">
        <img src="https://icon-icons.com/icons2/403/PNG/32/coin-us-dollar_40536.png">
        <span>${user.BALANCE}</span>
      </div>
    </div>
  </div>
`;

// Статус просьбы: актуально ли
const isActive = bool =>
{
  if(bool)
  {
    return `<div class="request_status">Статус: актуально</div>`;
  }
  return `<div class="request_status">Статус: неактуально</div>`;
}

// Вывод информации о просьбе
const renderRequest = request => `
  <div class="request">
    <form id="requests">
      <div class="request_task">${request.TASK}</div>
  ` + isActive(request.STATUS) +
  `   <div class="request_type">Категория: ${request.TYPE}</div>
      <div class="request_owner-name">Имя: ${request.OWNERNAME}</div>
      <div class="request_phone">Телефон: ${request.PHONE}</div>
      <div class="request_city">Город: ${request.CITY}</div>
      <div class="request_description">${request.DESCRIPTION}</div>
      <button class="button" type="submit">Принять</button>
      <div class="request_price">${request.PRICE}</div>
    </form>
  </div>
`;

// Вывод списка всех просьб
const getAllRequests = function() {
  createRequest({ path: "CARD", method: "GET"})
    .then(response => {
      document.querySelector("#requests").innerHTML = response
        .map(renderRequest)
        .join("");
      console.log("Результат запроса просьб", response);
    })
    .catch(err => {
      document.querySelector("#requests").innerHTML =
        "Не удалось получить список просьб";
      console.log("Ошибка при получении списка просьб", err);
    });
};

getAllRequests();

const accept = document.querySelector("#request");
accept.addEventListener("submit", event => {
  event.preventDefault();

  const data = getFieldData(event.target);
  console.log("main", "data", data);
  data.STATUS = true;

  toggleClass(".request", "loading");

  createRequest({ path: `CARD/${ID}`, method: "PUT" }, {}, data)
    .then(response => {
      toggleClass(".request", "loading");
      console.log("Книга добавлена", response);
    })
    .catch(() => {
      toggleClass(".add-book", "loading");
      console.log("Не удалось добавить книгу");
    });
});
