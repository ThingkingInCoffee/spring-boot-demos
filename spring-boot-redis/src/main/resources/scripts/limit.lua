redis.call('ZREMRANGEBYSCORE', KEYS[1], 0, tonumber(ARGV[2]) - tonumber(ARGV[3]))
if (redis.call('ZCARD', KEYS[1]) >= tonumber(ARGV[1])) then
   return '0'
end
redis.call('ZADD', KEYS[1], ARGV[2], ARGV[2])
redis.call('pexpire', KEYS[1], ARGV[3])
return '1'
