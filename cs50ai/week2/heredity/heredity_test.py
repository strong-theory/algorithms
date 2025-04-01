import heredity as h


def test_joint_probabilities():
    people = {'Harry': {'name': 'Harry', 'mother': 'Lily', 'father': 'James', 'trait': None}, 'James': {'name': 'James', 'mother': None, 'father': None, 'trait': True}, 'Lily': {'name': 'Lily', 'mother': None, 'father': None, 'trait': False}}

    p = h.joint_probability(people, {"Harry"}, {"James"}, {"James"})

    # test genes
    assert p['Lily']['Gene'][0] == 0.9504
    assert p['Lily']['Gene'][1] == 0.0
    assert p['Lily']['Gene'][2] == 0.0

    assert p['Harry']['Gene'][0] == 0.0
    assert p['Harry']['Gene'][1] == 1.0
    assert p['Harry']['Gene'][2] == 0.0

    assert p['James']['Gene'][0] == 0.0
    assert p['James']['Gene'][1] == 0.0
    assert p['James']['Gene'][2] == 1.0

    assert p['Harry']['Gene'][0] == 0.0
    assert p['Harry']['Gene'][1] == 1.0
    assert p['Harry']['Gene'][2] == 0.0

    assert p['James']['Gene'][0] == 0.0
    assert p['James']['Gene'][1] == 0.0
    assert p['James']['Gene'][2] == 1.0