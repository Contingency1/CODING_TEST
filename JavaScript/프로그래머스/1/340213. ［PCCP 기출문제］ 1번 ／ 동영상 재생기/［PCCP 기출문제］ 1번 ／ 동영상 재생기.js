function solution(video_len, pos, op_start, op_end, commands) {
  let POS = toNumber(pos);

  const VIDEO_LEN = toNumber(video_len);
  const OP_START = toNumber(op_start);
  const OP_END = toNumber(op_end);

  POS >= OP_START && POS <= OP_END ? (POS = OP_END) : pos;

  commands.forEach((x) => {
    if (x === "next") {
      POS + 10 > VIDEO_LEN ? (POS = VIDEO_LEN) : (POS += 10);
    }

    if (x === "prev") {
      POS - 10 < 0 ? (POS = 0) : (POS -= 10);
    }

    POS >= OP_START && POS <= OP_END ? (POS = OP_END) : null;
  });

  function toNumber(x) {
    return x
      .split(":")
      .reduce((acc, cur, idx) => (idx === 0 ? acc + +cur * 60 : acc + +cur), 0);
  }

  return (
    `${Math.floor(POS / 60)}`.padStart(2, "0") +
    ":" +
    `${POS % 60}`.padStart(2, "0")
  );
}
