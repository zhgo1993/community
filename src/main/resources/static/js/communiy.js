function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment-content").val();

    comment2target(questionId,1,content);
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var comment = $("#input-"+commentId).val();
    comment2target(commentId,2,comment);
}



function comment2target(targetId,type,content) {

    if(!content){
        alert("不能回复空内容！！");
        return;
    }


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/comment",
        data: JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
        }),
        success: function(msg){
            if(msg.code == 200){
                /*$("#comment_section").hide();*/
                window.location.reload();
            }else{
                if(msg.code == 2003){
                    var isAccepted = confirm(msg.message)
                    if(isAccepted ){
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.a286acba28802e6c&redirect_uri=http://localhost:8887/callback&state=1");
                        window.localStorage.setItem("closable","true");
                    }
                }else {
                    alert(msg.message);
                }
            }
        },
        dataType:"json"
    });
}



/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarurl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}