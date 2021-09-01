import state
import frontier

def search(n):
    s=state.create(n)
    # print(s)
    f=frontier.create(s)
    while not frontier.is_empty(f):
        s=frontier.remove(f)
        if state.is_target(s):
            return [s, f[1], f[4]]
        ns=state.get_next(s)
        for i in ns:
            frontier.insert(f,i)
    return 0

def evaluate_search(n):
    """
    Evaluate the search function by running it 100 times and returning key statistics.

    Input: Size of board nxn
    Output: Average max depth, Average number of states
    """
    max_depth = 0
    n_states = 0
    # Simulate the search algorithm 100 times and get the sum of max depth and num states:
    for i in range(100):
        answer = search(n)
        max_depth += answer[1]
        n_states += answer[2]
    # Get the average max depth and number of states
    av_max_depth = max_depth/100
    av_n_states = n_states/100
    return (av_max_depth, av_n_states)

# Run simulations on a 2x2 and 3x3 and 4x4 board:
for i in range(2, 4):
    evaluation = evaluate_search(i)
    print(f"Average Max Depth for {i} sized board:", evaluation[0])
    print(f"Average Number of States for {i} sized board:", evaluation[1])
    print()