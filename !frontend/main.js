// Вывод информации о пользователе
const renderUser = user => `
  <div class="user">
    <div class="user_pic">
      <a href="user.html?id=${user.ID}">
        <img src=${user.PIC_URL}>
      </a>
    </div>
    <div class="user_name">
      <a href="user.html?id=${user.ID}">${user.NAME}</a>
    </div>
  </div>
`;

// Вывод списка всех пользователей
const getAllUsers = function() {
  createRequest({ path: "USERS", method: "GET"})
    .then(response => {
      document.querySelector("#users").innerHTML = response
        .map(renderUser)
        .join("");
      console.log("Результат запроса пользователей", response);
    })
    .catch(err => {
      document.querySelector("#users").innerHTML =
        "Не удалось получить список пользователей";
      console.log("Ошибка при получении списка пользователей", err);
    });
};

getAllUsers();
