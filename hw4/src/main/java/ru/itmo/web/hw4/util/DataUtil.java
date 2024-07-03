package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.Color;
import ru.itmo.web.hw4.model.Post;
import ru.itmo.web.hw4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", Color.GREEN),
            new User(6, "pashka", "Pavel Mavrin", Color.BLUE),
            new User(9, "geranazavr555", "Georgiy Nazarov", Color.BLUE),
            new User(11, "tourist", "Gennady Korotkevich", Color.RED)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "Hello, Codeforces. Friends", "Hello, Codeforces!\n" +
                    "\n" +
                    "Unfortunately, today unexpectedly some errors occurred on the servers that we restarted before the round. We couldn't resolve them within 30 minutes. Half of the servers continue to work steadily.\n" +
                    "\n" +
                    "I don't want to risk hosting the round. A round preparation includes too much collaboration between the authors, coordinator, and testers. I have made the decision to postpone the round so that we can hold it in the future with confidence that everything will be fine.\n" +
                    "\n" +
                    "I apologize for the inconvenience. Today I will delve into what is happening and figure it out. There is no reason to think that something will not work for tomorrow's round.\n" +
                    "\n" +
                    "We will announce the new date for Round 875 later.\n" +
                    "\n" +
                    "Apologies again for the inconvenience, Mike\n" +
                    "M.\n" +
                    "P.S. Please cherish your loved ones while they are with you. Give them your attention. Bring joy to them, love them.\n", 1),
            new Post(2, "1884A - Простая конструкция", "Пусть \uD835\uDC66\n" +
                    "y\n" +
                    " ответ на задачу. Заметим, что \uD835\uDC66−\uD835\uDC65≤18\n" +
                    "y\n" +
                    "−\n" +
                    "x\n" +
                    "≤\n" +
                    "18\n" +
                    " и задачу можно решить перебором. Докажем этот факт.\n" +
                    "\n" +
                    "Если разница двух чисел больше 18\n" +
                    "18\n" +
                    ", то точно найдется последовательность из 10\n" +
                    "10\n" +
                    "чисел, где различается только последняя цифра. Соответственно, если сумма цифр числа с последней цифрой 0\n" +
                    "0\n" +
                    " равна \uD835\uDC65\n" +
                    "x\n" +
                    ", то были суммы цифр равные \uD835\uDC65,\uD835\uDC65+1,…,\uD835\uDC65+9\n" +
                    "x\n" +
                    ",\n" +
                    "x\n" +
                    "+\n" +
                    "1\n" +
                    ",\n" +
                    "…\n" +
                    ",\n" +
                    "x\n" +
                    "+\n" +
                    "9\n" +
                    ". Здесь ровно 10\n" +
                    "10\n" +
                    " последовательных чисел и для каждого \uD835\uDC58\n" +
                    "k\n" +
                    " от 1\n" +
                    "1\n" +
                    "до 10\n" +
                    "10\n" +
                    " найдется число из этого промежутка, которое делится на \uD835\uDC58\n" +
                    "k\n" +
                    ".", 6),
            new Post(3, "Сакральная истина, тайна мироздания раскрыта !!!", "Итак, все знают про МФТИ — один из лучших вузов страны. " +
                    "Сильная математика, сильное программирование, лучшая физика в стране. Но все ли так просто? мфти находиться в долгопрудном , " +
                    "студенты живут там и не выходят с территории дальше чем на 50 метров, из этого делаем вывод, что МФТИ — это секта. Закрытые кружки , " +
                    "непонятные секции , а у парней непонятные желания надевать фартук и чулки .Из этого делаем вывод, что МФТИ — это секта. Но и это еще не все. " +
                    "Как мы знаем, что МФТИ сотрудничает с Онлайн-школой Фоксфорд. Вам это ничего не напоминает ФОКСфорд. Где мы еще видим это видим этот видим это " +
                    "сочетание букв? Артем техноФОКС. Известный фурри-блогер. Так как у данной \"образовательной организации\" и у данного блогера совпадает, то делаем, " +
                    "что Артем получил образование там. Итак, мы выяснили — Фоксфорд — это фурри, а так как фоксфорд сотрудничает с МФТИ, можно сделать вывод — МФТИ — " +
                    "это фурри секта. Думайте", 9),
            new Post(5, "We should rename competitive programming to algorithm contests", "Pros:\n" +
                    "Better acronym\n" +
                    "Cons:\n" +
                    "None", 11)
    );
    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
