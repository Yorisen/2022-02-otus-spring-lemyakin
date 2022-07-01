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
            gridColAuthor.textContent = book.author;

            var gridColGenre = document.createElement('td');
            gridColGenre.className = "grid__col grid__col_text-right";
            gridColGenre.textContent = book.genre;

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
        };
    });

    function fillBookPopup(book) {
        clearBookPopup();
        fillBookInfo(book);
        showBookPopup();
    }
    function fillBookInfo(book) {
        if (book) {
            var bookIdInput = document.getElementById("book-id");
            var bookNameInput = document.getElementById("book-name");
            var bookAuthorInput = document.getElementById("author");
            var bookGenreInput = document.getElementById("genre");
            bookIdInput.value = book.id;
            bookNameInput.value = book.name;
            bookAuthorInput.value = book.author;
            bookGenreInput.value = book.genre;
        }
    }

    bookPopupButtonCancel.onclick = function() {
        hideBookPopup();
    }

    bookPopupButtonSave.onclick = function() {
        var formData = new FormData(bookPopup.getElementsByClassName("form__content")[0]);
        var requestJson = Object.fromEntries(formData);
        var requestJsonString = JSON.stringify(requestJson);

        if (requestJson && requestJson.id) {
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