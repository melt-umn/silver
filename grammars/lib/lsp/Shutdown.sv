
nonterminal ShutdownTask with priority, shutdownProcedure;

-- returns true if task1 has a higher or equal priotiy to task2
function higherShutdownPriority
Boolean ::= task1::ShutdownTask task2::ShutdownTask
{
  return task1.priority - task2.priority <= 0;
}
-- tasks with priority closer to 0
synthesized attribute priority :: Integer;
-- a shutdown procedure takes in and modified the state
synthesized attribute shutdownProcedure :: (State ::= State);

function handleShutdown
Pair<State ShutdownResult> ::= state::State input::ShutdownRequest io::IO
{
  -- clean up tasks to be done the order of these tasks is determined by
  -- there priority
  production attribute tasks::[ShutdownTask] with ++;
  tasks := [];
  local attribute orderedTasks :: [ShutdownTask] = sortBy(higherShutdownPriority, tasks);

  local attribute newState :: State = foldl(applyShutdownTaskToState, state, orderedTasks);

  return pair(shutdownServer(newState), nullShutdownResult());
}

function handleExit
State ::= state::State input::ExitNotification io::IO
{
  return exitServer(state);
}

function applyShutdownTaskToState
State ::= state::State task::ShutdownTask
{
  return task.shutdownProcedure(state);
}
