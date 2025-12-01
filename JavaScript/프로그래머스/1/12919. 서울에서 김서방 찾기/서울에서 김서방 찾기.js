const seoul = ["Jane", "Kim"];

function solution(seoul) {
    var answer = '';

    for (let x = 0; x < seoul.length; x++)
        {
            if (seoul[x] === "Kim"){
                answer = "김서방은 " + x +"에 있다";
            }
        }
    return answer;
}

console.log(solution(seoul));