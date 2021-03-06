(function(){
    var addBookButtons = Array.from(document.getElementsByClassName("button_add-book"));
    var booksGrid = document.getElementsByClassName("grid_books")[0];

    var bookPopup = document.getElementsByClassName("popup_book")[0];
    var bookPopupButtonCancel = document.getElementsByClassName("button_cancel")[0];
    var bookPopupButtonSave = document.getElementsByClassName("button_save")[0];

    fillBooksGrid();

    function fillBooksGrid() {
        clearBooksGrid();
        sendGetRequest("/books", parseJsonToBooksGridRows);
    }
    function parseJsonToBooksGridRows(books) {
        books.forEach( book => {
            var gridColName = document.createElement('td');
            gridColName.className = "grid__col grid__col_first";
            gridColName.textContent = book.name;

            var gridColAuthor = document.createElement('td');
            gridColAuthor.className = "grid__col";
            gridColAuthor.textContent = book.author.name + " " + book.author.surname + " " + book.author.patronymic;

            var gridColGenre = document.createElement('td');
            gridColGenre.className = "grid__col grid__col_text-right";
            gridColGenre.textContent = book.genre.name;

            var gridColLinks = document.createElement('td');
            gridColLinks.className = "grid__col grid__col_last grid__col_text-right";

            var linkDelete = document.createElement('span');
            linkDelete.className = "link link_margin-left";
            linkDelete.textContent = "Delete";
            linkDelete.setAttribute("book_id", book.id);
            linkDelete.onclick = function() {
                if (confirm("Are you sure you want to delete the book?")) {
                    sendDeleteRequest("/book/" + linkDelete.getAttribute("book_id"), fillBooksGrid);
                }
            }

            var linkEdit = document.createElement('span');
            linkEdit.className = "link link_margin-left";
            linkEdit.textContent = "Edit";
            linkEdit.setAttribute("book_id", book.id);
            linkEdit.onclick = function() {
                sendGetRequest("/book/" + linkEdit.getAttribute("book_id"), fillBookPopup);
                showBookPopup();
            }

            gridColLinks.appendChild(linkDelete);
            gridColLinks.appendChild(linkEdit);

            var gridRow = document.createElement('tr');
            gridRow.className = "grid__body grid__row";
            gridRow.appendChild(gridColName);
            gridRow.appendChild(gridColAuthor);
            gridRow.appendChild(gridColGenre);
            gridRow.appendChild(gridColLinks);

            booksGrid.appendChild(gridRow);
        });
    }
    function clearBooksGrid() {
        var gridElements = Array.from(booksGrid.getElementsByClassName("grid__body"));
        gridElements.forEach(gridElement => gridElement.parentNode.removeChild(gridElement));
    }


    addBookButtons.forEach(addBookButton => {
        addBookButton.onclick = function() {
            fillBookPopup();
            showBookPopup();
        };
    });

    function fillBookPopup(book) {
        clearBookPopup();

        fillAuthorsComboBox(book ? parseInt(book.author.id) : -1);
        fillGenresComboBox(book ? parseInt(book.genre.id) : -1);
        fillBookInfo(book);
    }
    function fillBookInfo(book) {
        if (book) {
            var bookIdInput = document.getElementById("book-id");
            var bookNameInput = document.getElementById("book-name");
            bookIdInput.value = book.id;
            bookNameInput.value = book.name;
        }
    }

    bookPopupButtonCancel.onclick = function() {
        hideBookPopup();
    }

    bookPopupButtonSave.onclick = function() {
        var formData = new FormData(bookPopup.getElementsByClassName("form__content")[0]);
        var requestJson = Object.fromEntries(formData);
        var requestJsonString = JSON.stringify(requestJson);

        if (requestJson && requestJson.id > 0) {
            sendPutRequest("/book", fillBooksGrid, requestJsonString);
        } else {
            sendPostRequest("/book", fillBooksGrid, requestJsonString);
        }

        hideBookPopup();
    }

    function hideBookPopup() {
        bookPopup.classList.add("popup_hide");
    }

    function showBookPopup() {
        bookPopup.classList.remove("popup_hide");
    }

    function clearBookPopup() {
        var inputs = Array.from(bookPopup.getElementsByClassName("form__input"));
        inputs.forEach(input => input.value = "");
        var comboBoxes = Array.from(bookPopup.getElementsByClassName("combo-box"));
        comboBoxes.forEach(comboBox => {
            comboBox.innerHTML = "";
            comboBox.selectedIndex = -1;
        });
    }

    function fillAuthorsComboBox(author_id) {
        sendGetRequest("/authors", parseJsonToAuthorsComboBoxOptions, author_id);
    }
    function parseJsonToAuthorsComboBoxOptions(authors, author_id) {
        var bookPopupAuthorsComboBox = document.getElementById("author");
        authors.forEach(author => {
            var option = document.createElement('option');
            option.value = author.id;
            option.innerHTML = author.name + " " + author.surname + " " + author.patronymic;

            bookPopupAuthorsComboBox.appendChild(option);
        });
        bookPopupAuthorsComboBox.value = author_id;
    }

    function fillGenresComboBox(genre_id) {
        sendGetRequest("/genres", parseJsonToGenresComboBoxOptions, genre_id);
    }
    function parseJsonToGenresComboBoxOptions(genres, genre_id) {
        var bookPopupGenresComboBox = document.getElementById("genre");
        genres.forEach(genre => {
            var option = document.createElement('option');
            option.value = genre.id;
            option.innerHTML = genre.name;

            bookPopupGenresComboBox.appendChild(option);
        });
        bookPopupGenresComboBox.value = genre_id;
    }

    function sendGetRequest(uri, callback, param) {
        fetch(uri)
        .then(response => response.json())
        .then(json => callback(json, param));
    }

    function sendDeleteRequest(uri, callback, param) {
        fetch(uri, {
            method: 'DELETE'
        })
        .then(json => callback(json, param));
    }

    function sendPostRequest(uri, callback, requestBody) {
        fetch(uri, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: requestBody
        })
        .then(json => callback(json));
    }

    function sendPutRequest(uri, callback, requestBody) {
        fetch(uri, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: requestBody
        })
        .then(json => callback(json));
    }
})();