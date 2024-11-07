
function solution(wallpaper) {
    let answer = [];
    let U = 0, D = 0, L = 0, R = 0;
    
    //배열안의 배열을 만들면 비교할때 편할듯하다.
    //배열안의 배열을 만들자
    for (let i = 0; i < wallpaper.length; i++) {
        wallpaper[i] =  wallpaper[i].split('')
    }
    //만들었으니까 이제 해야할것은
    //내부에있는 #을 확인하면서 가장 좌-상, 우-하들의 좌표를 뽑아내서 그것들을 활용하여 결과를 도출해야한다.
    //상 확인
    label: 
    for(let i = 0; i < wallpaper.length; i++) {
        for(let j = 0; j < wallpaper[0].length; j++) {
            if(wallpaper[i][j] === "#") {
                U = i;
                break label;
            }
        }
    }
    //좌 확인
    label:
    for(let i = 0; i < wallpaper[0].length; i++) {
        for(let j = 0; j < wallpaper.length; j++) {
            if(wallpaper[j][i] === "#") {
                L = i;
                break label;
            }
        }
    }
    //하 확인
    label:
    for(let i = wallpaper.length - 1; i >= 0; i--) {
        for(let j = 0; j < wallpaper[0].length; j++) {
            if(wallpaper[i][j] === "#") {
                D = ++i;
                break label;
            }
        }
    }
    //우 확인
    console.log(D)
    label:
    for(let i = wallpaper[0].length - 1; i >= 0; i--) {
        for(let j = 0; j < wallpaper.length; j++) {
            if(wallpaper[j][i] === "#") {
                R = ++i;
                break label;
            }
        }
    }
    answer.push(U,L,D,R)
    return answer;
}

