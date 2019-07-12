window.userId = parseInt(window.location.search.replace(/\D+/g,""));

// Отправка данных
const addCard = document.querySelector("#ad");
addCard.addEventListener("submit", event => {
  event.preventDefault();

  const data = getFieldData(event.target);
  console.log("main", "data", data);

  toggleClass(".ad", "loading");

  createRequest({ path: `CARD`, method: "POST" }, {}, data)
    .then(response => {
      toggleClass(".ad", "loading");
      console.log("Запрос добавлена", response);
    })
    .catch(() => {
      toggleClass(".ad", "loading");
      console.log("Не удалось добавить запрос");
    });
});

// Добавление ссылки на страницу назад на кнопку "Отмена"
// Была не доступна для инициализации сразу, т. к. не была проинициализирована переменная userId
const addLink = () =>
{
  document.querySelector("#link").href = "user.html?id=" + userId;
}
addLink();
