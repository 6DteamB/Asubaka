const calendarElement = document.getElementById("calendar");
const prevMonthButton = document.getElementById("prevMonth");
const nextMonthButton = document.getElementById("nextMonth");
const titleElement = document.getElementById("calendar-title");
const achievedButton = document.getElementById("achievedButton");

let currentYear, currentMonth, selectedDates = new Set();

document.addEventListener("DOMContentLoaded", function() {
	initCalendar();

	function initCalendar() {
		const currentDate = new Date();
		currentYear = currentDate.getFullYear();
		currentMonth = currentDate.getMonth();
		renderCalendar(currentYear, currentMonth);
		prevMonthButton.addEventListener("click", showPreviousMonth);
		nextMonthButton.addEventListener("click", showNextMonth);
		achievedButton.addEventListener("click", checkToday);
	}

	function renderCalendar(year, month) {
		// ... (前のコードは変更しません)

		// カレンダーを表示した後、カレンダーの外部にある既存の "mark-icon" 要素を削除します。
		removeMarkIcons();
	}

	// "mark-icon" 要素を削除する関数
	function removeMarkIcons() {
		const markIcons = document.querySelectorAll(".mark-icon");
		markIcons.forEach(markIcon => {
			markIcon.parentElement.removeChild(markIcon);
		});
	}

	function showPreviousMonth() {
		if (currentMonth === 0) {
			currentYear--;
			currentMonth = 11;
		} else {
			currentMonth--;
		}
		renderCalendar(currentYear, currentMonth);
	}

	function showNextMonth() {
		if (currentMonth === 11) {
			currentYear++;
			currentMonth = 0;
		} else {
			currentMonth++;
		}
		renderCalendar(currentYear, currentMonth);
	}

	function checkToday() {
		const currentDate = new Date();
		const year = currentDate.getFullYear();
		const month = currentDate.getMonth();
		const day = currentDate.getDate();
		const date = `${year}-${month + 1}-${day}`;

		// カレンダー内の "mark-icon" を非表示にする
		removeMarkIconsOutsideCalendar();

		// 今日の日付に対応する <input> 要素を検索します
		const dateCheckbox = calendarElement.querySelector(`input[data-date="${date}"]`);

		if (dateCheckbox) {
			// "mark.png" アイコンを表す新しい要素を作成します
			const markIcon = document.createElement("div");
			markIcon.classList.add("custom-checkbox", "checked", "mark-icon");

			// マークアイコンをチェックボックスの親の <td> 要素に追加します
			dateCheckbox.parentElement.appendChild(markIcon);
		}
	}

});
