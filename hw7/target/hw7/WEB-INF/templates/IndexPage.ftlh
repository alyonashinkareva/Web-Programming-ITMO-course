<#import "commons.ftlh" as c>

<@c.page>
    Welcome on Codeforces!
    <div class="articles">
        <template>
            <article>
                <div class="title"></div>
                <div class="information"></div>
                <div class="text"><p></p></div>
                <div class="footer">
                    <div class="left">
                        <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+173</span>
                        <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        <div class="creationTime"></div>
                        <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
            </article>
        </template>
        <div class="noData">
            No data.
        </div>
    </div>

    <script>
        $(function () {
            const $noData = $(".articles .noData");
            $noData.text("Loading...");

            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/index",
                data: {
                    action: "findAll"
                },
                success: function(response) {
                    $noData.remove();
                    response["articles"].forEach(function (article) {
                        const $tpl = $($(".articles").find("template").prop("content")).clone();
                        $tpl.find(".title").text(article["title"]);
                        $tpl.find(".text").text(article["text"]);
                        $tpl.find(".information").text("Made by " + article["userId"]);
                        $tpl.find(".footer .right .creationTime").text(article["creationTime"]);
                        $(".articles").append($tpl);
;                    });
                }
            });
        })
    </script>
</@c.page>
