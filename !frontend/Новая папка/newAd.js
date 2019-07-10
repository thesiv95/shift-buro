// Выбор пользователя
const selectUser = user => {
  if (user.ID == 1)
  {
    return `<option value="1" selected>User 1</option>`; //по дефу выбирается 1-й пользователь
  }
  return `<option value="${user.ID}">User ${user.ID}</option>`;
}

// Вывод ID всех пользователей
const getAllID = function() {
  createRequest({ path: "USERS", method: "GET"})
    .then(response => {
      document.querySelector("#select_control").innerHTML = response
        .map(selectUser)
        .join("");
      console.log("ID пользователей", response);
    })
    .catch(err => {
      document.querySelector("#select_control").innerHTML =
        "Не удалось получить список ID пользователей";
      console.log("Ошибка при получении списка ID", err);
    });
};

getAllID();

// Ивент на смену пользователей
const userSelector = document.querySelector('.select_control');
userSelector.addEventListener('change', event => {
  userId = event.target.value;
});

// Отправка объявления
const addCard = document.querySelector("#new-ad");
addCard.addEventListener("submit", event => {
  event.preventDefault();

  const data = getFieldData(event.target);
  console.log("main", "data", data);

  toggleClass(".new-ad", "loading");

    createRequest({ path: `CARD`, method: "POST" }, {},
                { OWNERID: userId, TASK: data.TASK, IS_ACTIVE: true})
    .then(response => {
      toggleClass(".new-add", "loading");
      console.log("Объявление добавлено", response);
    })
    .catch(() => {
      toggleClass(".new-add", "loading");
      console.log("Не удалось добавить объявление");
    });
});
