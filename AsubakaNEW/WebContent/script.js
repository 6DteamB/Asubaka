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
		const daysInMonth = new Date(year, month + 1, 0).getDate();
		const firstDay = new Date(year, month, 1);
		const startingDay = (firstDay.getDay() + 6) % 7;

		let calendarHtml = '<table>';
		calendarHtml += '<tr><th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th>土</th><th>日</th></tr>';
		let day = 1;

		for (let i = 0; i < 6; i++) {
			calendarHtml += '<tr>';

			for (let j = 0; j < 7; j++) {
				if (i === 0 && j < startingDay) {
					calendarHtml += '<td></td>';
				} else if (day <= daysInMonth) {
					const date = `${year}-${month + 1}-${day}`;
					const isChecked = selectedDates.has(date) ? 'checked' : '';
					calendarHtml += `<td><span class="date">${day}</span><input type="checkbox" data-date="${date}" ${isChecked}></td>`;
					day++;
				} else {
					calendarHtml += '<td></td>';
				}
			}

			calendarHtml += '</tr>';
		}

		calendarHtml += '</table';
		calendarElement.innerHTML = calendarHtml;
		titleElement.textContent = `${year}年${month + 1}月`;

		const checkboxes = calendarElement.querySelectorAll('input[type="checkbox"]');
		checkboxes.forEach(checkbox => {
			checkbox.addEventListener("change", toggleDate);
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

	function toggleDate(event) {
		const date = event.target.dataset.date;
		if (selectedDates.has(date)) {
			selectedDates.delete(date);
		} else {
			selectedDates.add(date);
		}
	}

	function checkToday() {
		const currentDate = new Date();
		const year = currentDate.getFullYear();
		const month = currentDate.getMonth();
		const day = currentDate.getDate();
		const date = `${year}-${month + 1}-${day}`;

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