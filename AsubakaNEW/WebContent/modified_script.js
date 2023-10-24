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
                const isChecked = selectedDates.has(date) ? 'visible' : 'hidden';
                calendarHtml += `<td><span class="date">${day}</span><img src="/images/nikukyu_kuro.png" data-date="${date}" class="${isChecked}" alt="Cat Icon"></td>`;
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

    // Find the cat icon for today's date
    const catIcon = calendarElement.querySelector(`img[data-date="${date}"]`);

    if (catIcon) {
        // Show the cat icon
        catIcon.classList.remove("hidden");
        catIcon.classList.add("visible");

        // Record the selected date
        selectedDates.add(date);
    }
}

// Event listener to redirect to reward page
document.getElementById("redirectToRewardPage").addEventListener("click", function() {
    var targetUrl = "reward.jsp";
    window.location.href = targetUrl;
});

});
